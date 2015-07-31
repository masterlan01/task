package com.master.task.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Ответы на вопрос
 */

@Entity
@Table(name = "answers", catalog = "task")
public class Answer implements Serializable {

	private static final long serialVersionUID = -5470090944414208496L;

	private int id;

	private String text;

	private boolean verity;

	@ManyToOne
	@JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
	private int question_id;

	public Answer() {
	}

	public Answer(int question_id, String text, boolean verity) {
		this.text = text;
		this.verity = verity;
		this.question_id = question_id;
	}

	// ID ответа
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// текст ответа
	@Size(min = 1, max = 255)
	@Column(name = "TEXT", nullable = false)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// признак верности ответа
	@Column(name = "VERITY")
	public boolean isVerity() {
		return verity;
	}

	public void setVerity(boolean verity) {
		this.verity = verity;
	}

	// ID вопроса
	@Column(name = "QUESTION_ID", nullable = false)
	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	@Override
	public String toString() {
		return this.text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + question_id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + (verity ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (id != other.id)
			return false;
		if (question_id != other.question_id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (verity != other.verity)
			return false;
		return true;
	}

}
