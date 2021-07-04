package com.jade.myapp.board.service;

import java.util.List;

import com.jade.myapp.board.model.BoardCategory;

public interface IBoardCategoryService {

	List<BoardCategory> selecetAllCategory();
	List<BoardCategory> selectAllCategoryByClass1(int class1);
	void insertNewCatogory(BoardCategory boardCategory);
	void updateCategory(BoardCategory boardCategory);
	void deleteCategory(int categoryId);
	
}
