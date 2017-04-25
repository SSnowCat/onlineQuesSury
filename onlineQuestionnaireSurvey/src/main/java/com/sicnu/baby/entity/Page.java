package com.sicnu.baby.entity;

/**
 * 分页对应的实体类
 */
public class Page {
	/**
	 * 总条数
	 */
	private int totalNumber;
	/**
	 * 当前第几页
	 */
	private int currentPage;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 每页显示条数
	 */
	private int pageNumber = 5;
	/**
	 * 数据库中limit的参数，从第几条开始取
	 */
	private int dbIndex;
	/**
	 * 数据库中limit的参数，一共取多少条
	 */
	private int dbNumber;
	
	/**
	 * 根据当前对象中属性值计算并设置相关属性值
	 */
	public void count() {
		// 计算总页数
		int totalPageTemp = this.totalNumber / this.pageNumber;
		int plus = (this.totalNumber % this.pageNumber) == 0 ? 0 : 1;
		totalPageTemp = totalPageTemp + plus;
		if(totalPageTemp <= 0) {
			totalPageTemp = 1;
		}
		this.totalPage = totalPageTemp;
		
		// 设置当前页数
		// 总页数小于当前页数，应将当前页数设置为总页数
		if(this.totalPage < this.currentPage) {
			this.currentPage = this.totalPage;
		}
		// 当前页数小于1设置为1
		if(this.currentPage < 1) {
			this.currentPage = 1;
		}
		
		// 设置limit的参数
		this.dbIndex = (this.currentPage - 1) * this.pageNumber;
		this.dbNumber = this.pageNumber;
	}

	/**
	 * 获取数据总量
	 * @return int
	 */
	public int getTotalNumber() {
		return totalNumber;
	}

	/**
	 * 设置数据总量
	 * @param totalNumber
	 */
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}

	/**
	 * 获取当前页面
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页面
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获取页面总量
	 * @return int
	 */
	public int getTotalPage() {
		return totalPage;
	}
    /**
     * 获取每页显示条数
     * @return int
     */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置每页显示条数
	 * @param pageNumber
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		this.count();
	}

	/**
	 * 获取数据库limit第一个参数
	 * @return int
	 */
	public int getDbIndex() {
		return dbIndex;
	}

	/**
	 * 设置数据库limit第一个参数
	 * @param dbIndex
	 */
	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	/**
	 * 获取数据库limit第二个参数
	 * @return
	 */
	public int getDbNumber() {
		return dbNumber;
	}

	/**
	 * 设置数据库limit第二个参数
	 * @param dbNumber
	 */
	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}
}
