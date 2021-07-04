package com.jade.myapp.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jade.myapp.board.dao.IBoardRepository;
import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.model.BoardUploadFile;

@Service
public class BoardService implements IBoardService{

	@Autowired
	IBoardRepository boardRepository;

	@Transactional
	@Override
	public void insertArticle(Board board) {
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		boardRepository.insertArticle(board);
		
	}

	@Transactional
	@Override
	public void insertArticle(Board board, BoardUploadFile file) {
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		boardRepository.insertArticle(board);
		
		if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
			file.setBoardId(board.getBoardId());
			file.setFileId(boardRepository.selectMaxArticleNo()+1);
			boardRepository.insertFileDate(file);
		}
		
	}

	@Override
	public List<Board> selectArticleListByCategory(int categoryId, int page) {
		int start = (page-1)*10;
		return boardRepository.selectArticleListByCategory(categoryId, start, start+10);
	}

	@Override
	public List<Board> selectArticleListByCategory(int categoryId) {

		return boardRepository.selectArticleListByCategory(categoryId, 0, 100);
	}

	@Transactional
	@Override
	public Board selectArticle(int boardId) {
		boardRepository.updateReadCount(boardId); // 조회수 1 증가
		return boardRepository.selectArticle(boardId);
	}

	@Override
	public BoardUploadFile getFile(int fileId) {
		
		return boardRepository.getFile(fileId);
	}

	@Transactional
	@Override
	public void replyArticle(Board board) {
		boardRepository.updateReplyNumber(board.getMasterId(), board.getReplyNumber());
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		board.setReplyNumber(board.getReplyNumber()+1);
		board.setReplyStep(board.getReplyStep()+1);
		boardRepository.replyArticle(board);
	}

	@Transactional
	@Override
	public void replyArticle(Board board, BoardUploadFile file) {
		boardRepository.updateReplyNumber(board.getMasterId(), board.getReplyNumber());
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		board.setReplyNumber(board.getReplyNumber()+1);
		board.setReplyStep(board.getReplyStep()+1);
		boardRepository.replyArticle(board);
		if(file !=null && file.getFileName() != null && !file.getFileName().equals("")) {
			file.setBoardId(board.getBoardId());
			boardRepository.insertFileDate(file);
		}
	}

	@Override
	public String getPassword(int boardId) {
		return boardRepository.getPassword(boardId);
	}

	@Override
	public void updateArticle(Board board) {
		boardRepository.updateArticle(board);
	}

	@Transactional
	@Override
	public void updateArticle(Board board, BoardUploadFile file) {
		boardRepository.updateArticle(board);
		if(file !=null && file.getFileName() != null && !file.getFileName().equals("")) {
			file.setBoardId(board.getBoardId());
			
			if(file.getFileId() >0) {
				boardRepository.updateFileData(file);
			}else {
				boardRepository.insertFileDate(file);
			}
		}
	}

	@Override
	public Board selectDeleteArticle(int boardId) {
		return boardRepository.selectDeleteArticle(boardId);
	}

	@Transactional
	@Override
	public void deleteArticle(int boardId, int replyNumber) {
		if(replyNumber >0) {
			boardRepository.deleteReplyFileData(boardId);
			boardRepository.deleteArticleByBoardId(boardId);
		}else if(replyNumber ==0) {
			boardRepository.deleteFileData(boardId);
			boardRepository.deleteArticleByMasterId(boardId);
		}else {
			throw new RuntimeException("Wrong_replyNumber");
		}
	}

	@Override
	public int selectTotalArticleCount() {
		return boardRepository.selectTotalArticleCount();
	}

	@Override
	public int selectTotalArticleCountByCategoryId(int categoryId) {
		return boardRepository.selectTotalArticleCountByCategoryId(categoryId);
	}

	@Override
	public List<Board> searchListByContentKeyword(String keyword, int page) {
		int start = (page-1)*10;
		return boardRepository.searchListByContentKeyword("%"+keyword+"%",start,start+10);
	}

	@Override
	public int selectTotalArticleCountByKeyword(String keyword) {
		return boardRepository.selectTotalArticleCountByKeyword("%"+keyword+"%");
	}

	
}
