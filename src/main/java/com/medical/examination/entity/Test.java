package com.medical.examination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Table(name = "test")
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="test_name")
	private String testName;
	@Column(name="cost_price")
	private Double costPrice;
	@Column(name="sell_price")
	private Double sellPrice;
	@ManyToOne
	@JoinColumn(name = "test_type_id")
	private TestType testType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public TestType getTestType() {
		return testType;
	}
	public void setTestType(TestType testType) {
		this.testType = testType;
	}
	
}
