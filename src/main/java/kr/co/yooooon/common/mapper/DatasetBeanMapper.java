package kr.co.yooooon.common.mapper;


import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.DataSetList;
import com.tobesoft.xplatform.data.DataTypes;
import com.tobesoft.xplatform.data.PlatformData;
import kr.co.yooooon.common.annotation.ColumnName;
import kr.co.yooooon.common.annotation.Dataset;
import kr.co.yooooon.common.annotation.Remove;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Component
public class DatasetBeanMapper {

    public <T> List<T> datasetToBeans(PlatformData inData, Class<T> classType) throws Exception {
        String datasetName = getDataSetName(classType);  //dataset의 name을 얻어옴
        DataSet dataset = inData.getDataSet(datasetName); //dataset들 얻음
        List<T> beanList = new ArrayList<T>();
        T bean = null;
        int rowCount = dataset.getRowCount();  //데이터셋 총갯수 
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {  //데이터셋의 수만큼 반복 
            bean = getBean(dataset, classType, rowIndex);  //bean의 개수만큼 뽑아서 bean에 담음 
            beanList.add(bean);
        }

        rowCount = dataset.getRemovedRowCount();  //Dataset 에서 삭제된 Row 수를 구하는 Method 입니다.
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            bean = getDeletedBean(dataset, classType, rowIndex);
            beanList.add(bean);
        }
        return beanList;
    }

    
    public <T> T datasetToBean(PlatformData inData, Class<T> classType) throws Exception {
        T bean = null;
        String datasetName = getDataSetName(classType);   //dataset의 name을 얻어옴.
        DataSet dataset = inData.getDataSet(datasetName);  //dataset얻음 

        if(dataset.getRemovedRowCount() == 0)  //삭제한 행의 갯수 X
            bean = getBean(dataset, classType, 0);   //TO 객체에 값 담음 
        else
            bean = getDeletedBean(dataset, classType, 0);
        return bean;
    }

    public <T> void beansToDataset(PlatformData outData, List<T> beanList, Class<T> classType) throws Exception {
        Map<String, String> nameMap = new HashMap<String, String>();

        DataSetList datasetList = outData.getDataSetList(); // 비어있는 setlist
        String datasetName = getDataSetName(classType); //dataset 어노테이션 이름을 가져온다
        DataSet dataset = new DataSet(datasetName); // dataset 생성
        datasetList.add(dataset); // 보낼데이터를 담음
        
        Field[] fields = classType.getDeclaredFields();
        for(Field field : fields)
            setColumnName(dataset, nameMap, field);
        for(T bean : beanList)
            setColumnValue(dataset, nameMap, bean);
    }


    public <T> void beanToDataset(PlatformData outData, T bean, Class<T> classType) throws Exception {
        Map<String, String> nameMap = new HashMap<String, String>();
        DataSetList datasetList = outData.getDataSetList();  //비어있는 상태 

        String datasetName = getDataSetName(classType);   //데이터셋 이름 설정 
        DataSet dataset = new DataSet(datasetName);   //dataset생성 

        datasetList.add(dataset);  // 보낼 데이터 담음 

        if(bean != null) {
        	Field[] fields = classType.getDeclaredFields();  //멤버변수들이 담김
            for(Field field : fields)  //필드 하나씩 
                setColumnName(dataset, nameMap, field);   //어노테이션 확인함 
            setColumnValue(dataset, nameMap, bean);
        }
    }

    public void mapToDataset(PlatformData outData, List<Map<String, Object>> mapList, String datasetName) throws Exception {
        DataSetList datasetList = outData.getDataSetList();
        DataSet dataset = new DataSet(datasetName);
        datasetList.add(dataset);

        for(String key : mapList.get(0).keySet()) {
            String columnName = key.toLowerCase();
            dataset.addColumn(columnName, DataTypes.STRING, 256);
        }

        int rowIndex = 0;
        for(Map<String, Object> map : mapList) {
            rowIndex = dataset.newRow();
            for(String key : map.keySet()) {
                Object columnValue = map.get(key);
                dataset.set(rowIndex, key.toLowerCase(), columnValue);
            }
        }
    }

    public List<Map<String, Object>> datasetToMap(PlatformData inData, String datasetName) throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<>();

        DataSet dataset = inData.getDataSet(datasetName);
        int rowCount = dataset.getRowCount();
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", dataset.getRowTypeName(rowIndex));

            for(int colIndex = 0; colIndex < dataset.getColumnCount(); colIndex++) {
                String key = dataset.getColumn(colIndex).getName();
                Object value = dataset.getObject(rowIndex, key);
                map.put(formattingToCamel(key), value);
            }
            mapList.add(map);
        }

        rowCount = dataset.getRemovedRowCount();
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", dataset.getRowTypeName(rowIndex));

            for(int colIndex = 0; colIndex < dataset.getColumnCount(); colIndex++) {
                String key = dataset.getColumn(colIndex).getName();
                Object value = dataset.getObject(rowIndex, key);
                map.put(formattingToCamel(key), value);
            }
            mapList.add(map);
        }
        return mapList;
    }

    private <T> String getDataSetName(Class<T> classType) {
        if(classType.isAnnotationPresent(Dataset.class))   //클래스에 @Dataset 어노테이션이 있는지 확인 true&false
            return classType.getAnnotation(Dataset.class).name(); //Dataset의 name을 얻음.
        else
            return "ds" + classType.getName().replace("Bean", "");   //어노테이션이 없다면 ds_클래스이름+Bean 
    }
    
    

    private String getColumnName(Method method) {
        String columnName = null;
        Annotation[] annotations = method.getAnnotations();//메소드에 어노테이션 확인
        for (Annotation annotation : annotations) {
            if (annotation instanceof ColumnName) {  //ColumName 이라는 어노테이션이 있는지 확인
                String annotaionName = ((ColumnName) annotation).name();  //어노테이션의 name을 컬럼이름으로 
                columnName = annotaionName;
            }
        }
        if (annotations.length == 0)
            columnName = formattingToSnake(method.getName());  //스네이크 표기법으로 변환한 칼럼명 
        return columnName;
    }

    
    
    
    private void setColumnName(DataSet dataset, Map<String, String> nameMap, Field field) {
        	ColumnName column = field.getAnnotation(ColumnName.class);
            Remove remove = field.getAnnotation(Remove.class);
            if(column != null) {
                dataset.addColumn(column.name(), getDataType(field));
                nameMap.put(column.name(), field.getName());
            } else if(column == null && remove == null) {
                JoinColumn list = field.getAnnotation(JoinColumn.class);
                if(list==null){
                    String columnName = formattingToSnake(field.getName());  //스네이크 표기법 + 대문자로
                    dataset.addColumn(columnName, getDataType(field)); // 엑스플랫폼타입 컬럼
                    nameMap.put(columnName, field.getName());
                }
            }
        
    }

    private <T> void setColumnValue(DataSet dataset, Map<String, String> nameMap, T bean) throws Exception {
        int rowIndex = dataset.newRow();

        for(String columnName : nameMap.keySet()) {
            String fieldName = nameMap.get(columnName);
            Field value = bean.getClass().getDeclaredField(fieldName.trim());
            value.setAccessible(true);									// Priavte 로 되어있는 객체에 접근하기 위해서 사용
            dataset.set(rowIndex, columnName, value.get(bean));			// 값을 얻기 위해서 사용
        }
    }

    private <T> T getBean(DataSet dataset, Class<T> classType, int rowIndex) throws Exception {
        T bean = classType.newInstance();   //TO객체생성
        Method[] methods = classType.getDeclaredMethods();  //모든 메소드를 가져온다.  setter, getter  
        Method statusMethod = classType.getMethod("setStatus", String.class);   //  setStatus 메서드 1개 
        String rowType = null;
        switch(dataset.getRowTypeName(rowIndex)){
        	case "inserted" :
        		rowType = "insert";
        		break;
        	case "updated" :
        		rowType = "update";
        		break;
        }
        statusMethod.invoke(bean, rowType);  // statusMethod메서드 실행
        for(Method method : methods) {
            if(method.getName().startsWith("set")) {  //set으로 시작하는 setter 메소드들 
                String columnName = getColumnName(method);     //메소드에 어노테이션이 있는지 검사하고 컬럼명을 스네이크 형식으로 변경 
                System.out.println("qweqweqweqweqe1111"+columnName);  //칼럼명
                if(columnName != null) {
                    Object columnValue = dataset.getObject(rowIndex, columnName);
                    System.out.println("qweqweqweqweqe22222"+columnValue);  //넘어오는값 
                    if(columnValue != null)
                        method.invoke(bean, columnValue);    //set메소드들로 bean에 담음 
                }
            }
        }
        return bean;
    }
    
    
    
    private <T> T getDeletedBean(DataSet dataset, Class<T> classType, int rowIndex) throws Exception {
        T bean = classType.newInstance();
        Method[] methods = classType.getDeclaredMethods();
        Method statusMethod = classType.getMethod("setStatus", String.class);
        statusMethod.invoke(bean, "delete");
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String columnName = getColumnName(method);
                if (columnName != null) {
                    Object columnValue = dataset.getRemovedData(rowIndex, columnName);  
                    if (columnValue != null)
                        method.invoke(bean, columnValue);
                }
            }
        }
        return bean;
    }
    private int getDataType(Field field) {  //엑스플랫폼의 데이터 타입으로 변환 
        Class<?> returnType = field.getType();
        if(returnType == Date.class)
            return DataTypes.DATE;
        else if(returnType == String.class)
            return DataTypes.STRING;
        else if(returnType == int.class || returnType == boolean.class)
            return DataTypes.INT;
        else if(returnType == BigDecimal.class)
            return DataTypes.BIG_DECIMAL;
        else if(returnType == double.class )
            return DataTypes.DOUBLE;
        else if(returnType == byte[].class)
            return DataTypes.BLOB;
        else
            return DataTypes.NULL;
    }

    private String formattingToSnake(String name) {  //스네이크 표기법으로 바꿈
        String regex = "([a-z])([A-Z])";  //캡쳐 - 괄호로 구분 ,  (xxx)(XXX) 
        String replacement = "$1_$2";  //  xxx_XXX


        if(name.startsWith("set") || name.startsWith("get"))
            name = name.substring(3);
        // aA -> a_A
        name = name.replaceAll(regex, replacement);   // 변환하고자하는 대상이 될 문자열 , 변환할 문자 값  
        // return A_A
        return name.toUpperCase();

    }

    private String formattingToCamel(String name) {

        if(name.startsWith("set") || name.startsWith("get"))
            name = name.substring(3);
        String camel = WordUtils.capitalizeFully(name, new char[]{'_'}).replaceAll("_", "");
        return camel.substring(0, 1).toLowerCase() + camel.substring(1);
    }

}
