package org.zerock.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.CarVO;
import org.zerock.mapper.SelectionMapper;

@Service
public class SelectionService {

	@Autowired
	SelectionMapper selectionMapper;
	// 제조사 list select 후 ArrayList로 반환
	public ArrayList<String> selectBrands(){
		return selectionMapper.selectBrands();
	}
	
	public ArrayList<String> selectCars(String brand){
		return selectionMapper.selectCars(brand);
	}
	
	public ArrayList<CarVO> selectYears(String brand, String car){
		return selectionMapper.selectYears(brand,car);
	}
}
