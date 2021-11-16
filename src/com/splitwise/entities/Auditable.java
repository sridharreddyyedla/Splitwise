package com.splitwise.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modified;
	
	@Override
	public boolean equals(Object obj) {
		if(this== obj) return true;
		if(obj==null || obj.getClass() != getClass()) return false;
		Auditable auditable = (Auditable) obj;
		if(id==null || auditable.id==null) return false;
		return id.equals(auditable.id);
	}
	
	@Override
	public int hashCode() {
		return id==null? super.hashCode(): id.hashCode();
	}

	@Override
	public String toString() {
		return "Auditable [id=" + id + "]";
	}

}
