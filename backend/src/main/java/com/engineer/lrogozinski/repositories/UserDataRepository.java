package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.domain.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDataRepository extends CrudRepository<UserData, Integer> {

    List<UserData> findAllByFavouriteEventCategoriesContains(EventCategory eventCategory);
}
