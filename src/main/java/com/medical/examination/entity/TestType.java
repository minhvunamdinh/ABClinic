package com.medical.examination.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test_type")
public class TestType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "type_name")
	@NotNull(message = "Thông tin bắt buộc!")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 1, max = 50, message = "Độ dài phải từ 1 đến 50 ký tự")
	private String typeName;
	@Column(name = "status")
	private Integer status;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "testType", cascade = CascadeType.ALL)
	@Where(clause = "status = 1") //Thêm điều kiện search listTest với status = 1 (Đã kích hoạt)
//	@JsonIgnore
	List<Test> lstTest;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<Test> getLstTest() {
		return lstTest;
	}
	public void setLstTest(List<Test> lstTest) {
		this.lstTest = lstTest;
	}
	
}
