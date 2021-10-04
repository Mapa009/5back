package kr.co.yooooon.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.yooooon.base.sf.BaseServiceFacade;
import kr.co.yooooon.base.to.BoardTO;
import kr.co.yooooon.common.exception.DataAccessException;
import net.sf.json.JSONObject;

//@RequestMapping(value= "/base/listBoard.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
@Controller
public class BoardController {
	
	@Autowired
	private BaseServiceFacade baseSF;
	PrintWriter out = null;
	
    @GetMapping("/base/registForm")
    public String insertPost(HttpServletResponse response,
    		@RequestParam("boardSeq")String boardSeq,@RequestParam("today")String today,
    		@RequestParam("rrrr")String rrrr,@RequestParam("mm")String mm, 
    		@RequestParam("dd")String dd,@RequestParam("author")String author,
    		@RequestParam("title")String title,@RequestParam("content")String content) throws IOException {
    	HashMap<String, Object> map=new HashMap<>();
    	JSONObject json = new JSONObject();
    	try {
    	BoardTO bt = new BoardTO();
    	bt.setBoardSeq(boardSeq);
    	bt.setAuthor(author);
    	bt.setTitle(title);
    	bt.setContent(content);
    	bt.setToday(today);
    	bt.setMm(mm);
    	bt.setDd(dd);
    	baseSF.addBoard(bt);
    	
    	map.put("errorCode",0);
		map.put("errorMsg","success");
					
	}  catch (DataAccessException dae){
		map.clear();
		map.put("errorCode", -1);
		map.put("errorMsg", dae.getMessage());
	}
    	json = JSONObject.fromObject(map);
    	response.setHeader("Content-Type", "application/xml"); 
    	response.setContentType("text/xml;charset=UTF-8"); 
    	response.setCharacterEncoding("utf-8"); 
    	response.getWriter().print(json);
        return null;
    }
    
    //@RequestMapping(value = "/base/listBoard.do", produces = "application/text; charset=utf8")
    @RequestMapping( path = "/base/listBoard", method = RequestMethod.GET)
    public ArrayList<BoardTO> findBoardList(HttpServletResponse response) throws IOException {
    	HashMap<String, Object> map=new HashMap<>();
    	JSONObject json = new JSONObject();
    	
    	try {
	    	out=response.getWriter();
	    
	    	ArrayList<BoardTO> array=baseSF.findBoardList();
	    	map.put("json", array);

	    	map.put("errorCode",0);
			map.put("errorMsg","success");
						
		}  catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
    	json = JSONObject.fromObject(map);
    	response.setHeader("Content-Type", "application/xml"); 
    	response.setContentType("text/xml;charset=UTF-8"); 
    	response.setCharacterEncoding("utf-8"); 
    	response.getWriter().print(json);

    	//out.println(json);
    	
        return null;
    }
    
    @RequestMapping( path = "/base/findPost", method = RequestMethod.GET)
    public ArrayList<BoardTO> findPost(@RequestParam("useBoardSeq")String useBoardSeq, 
    		@RequestParam("useRefSeq")String useRefSeq, HttpServletResponse response) throws IOException {
    	HashMap<String, Object> map=new HashMap<>();
    	JSONObject json = new JSONObject();
    	BoardTO bt = new BoardTO();
    	try {
	    	out=response.getWriter();
	    	
	    	bt.setBoardSeq(useBoardSeq);
	    	bt.setRefSeq(useRefSeq);
	    	ArrayList<BoardTO> array=baseSF.findPost(bt);
	    	map.put("json", array);

	    	map.put("errorCode",0);
			map.put("errorMsg","success");
						
		}  catch (DataAccessException dae){
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
    	json = JSONObject.fromObject(map);
    	response.setHeader("Content-Type", "application/xml"); 
    	response.setContentType("text/xml;charset=UTF-8"); 
    	response.setCharacterEncoding("utf-8"); 
//    	response.getWriter().print(json);

    	out.println(json);
    	
        return null;
    }
}