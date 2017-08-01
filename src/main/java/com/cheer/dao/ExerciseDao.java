package com.cheer.dao;

import com.cheer.domian.Exercise;

public interface ExerciseDao
{
	void save(Exercise exe);
	Exercise find(int id);
}
