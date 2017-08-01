package com.cheer.service.impl;

import com.cheer.dao.ExerciseDao;
import com.cheer.dao.impl.ExerciseDaoImpl;
import com.cheer.domian.Exercise;
import com.cheer.service.ExerciseService;

public class ExerciseServiceImpl implements ExerciseService
{
	public void save(Exercise exe)
	{
		ExerciseDao exd=new ExerciseDaoImpl();
		exd.save(exe);
	}
}
