package com.jade.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.model.BoardUploadFile;

public interface IBoardRepository {
	
	int selectMaxArticleNo();
	int selectMaxFileId();
	
	void insertArticle(Board board);
	void insertFileDate(BoardUploadFile file);
	
	List<Board> selectArticleListByCategory(@Param("categoryId")int categoryId, @Param("start")int start, @Param("end") int end);
	
	Board selectArticle(int boardId);
	BoardUploadFile getFile(int fileId);
	
	void updateReadCount(int boardId);
	void updateReplyNumber(@Param("masterId")int masterId);
	void replyArticle(Board board);
	
	String getPassword(int boardId);
	void updateArticle(Board board);
	void updateFileData(BoardUploadFile file);
	
	void deleteFileData(int boardId);
	void deleteReplyFileData(int boardI);
	Board selectDeleteArticle(int boardId);
	void deleteArticleByBoardId(int boardId);
	void deleteArticleByMasterId(int boardId);
	
	int selectTotalArticleCount();
	int selectTotalArticleCountByCategoryId(int categoryId);
	List<Board> searchListByContentKeyword(String keyword);
	
	
}



















