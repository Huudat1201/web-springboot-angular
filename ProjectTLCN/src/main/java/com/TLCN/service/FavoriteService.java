package com.TLCN.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.TLCN.entity.Favorite;
import com.TLCN.model.BestSellerModel;

public interface FavoriteService {

	Favorite create(int id);

	List<Favorite> getListFavoriteByEmail();

	void delete(int id);

	Favorite getOneFavorite(int id);

	List<BestSellerModel> getListBestSellerProduct(Pageable topFour);

}
