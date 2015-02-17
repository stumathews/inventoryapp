package com.example.inventoryapp.tests;

public interface IDAOTests {

	public abstract void test_getAll() throws Exception;

	public abstract void test_insert() throws Exception;

	public abstract void test_truncate() throws Exception;

	public abstract void test_findByName() throws Exception;

	public abstract void test_findById() throws Exception;

	public abstract void test_update() throws Exception;

}
