package com.medical.examination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Table(name = "test")
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="test_name")
	@NotNull(message = "Thông tin bắt buộc!")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 1, max = 50, message = "Độ dài phải từ 1 đến 50 ký tự")
	private String testName;
	@Column(name="cost_price")
	//@NotNull(message = "Thông tin bắt buộc!")
	private Double costPrice;
	@Column(name="sell_price")
	//@NotNull(message = "Thông tin bắt buộc!")
	private Double sellPrice;
	@ManyToOne
	@JoinColumn(name = "test_type_id")
	@NotNull(message = "Thông tin bắt buộc!")
	private TestType testType;
	@Column(name = "status")
	private Long status;
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}
