package com.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/write") // 
	public String boardWriteForm() {
		return "boardWrite";
	}

	@PostMapping("/board/writepro")
	public String boardWritePro(Board board) {

		boardService.write(board);
		return "";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		model.addAttribute("list", boardService.boardList());
		return "boardlist";
	}
	
	@GetMapping("/board/view")
	public String boardView(Model model, Integer id) {
		model.addAttribute("board", boardService.boardview(id));
		return "boardview";
	}
	
	@GetMapping("/board/delete")
	public String boardDelete(Integer id) {
		boardService.boardDelete(id);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/modify/{id}")
	public String boardModify(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("board", boardService.boardview(id));
		return "boardmodify";
	}
	
	@PostMapping("/board/update/{id}")
	public String boardUpdate(@PathVariable("id") Integer id, Board board) {
		
		Board boardTemp = boardService.boardview(id);
		boardTemp.setTitle(board.getTitle());
		boardTemp.setContent(board.getContent());
		
		boardService.write(boardTemp);
		return "redirect:/board/list";
	}
}
