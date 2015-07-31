package com.master.task.entity;

import org.springframework.stereotype.Component;

@Component
public class UserAnswer {

	private int AnswerID;

	public UserAnswer(int answerID) {
		this.setAnswerID(answerID);

	}

	public UserAnswer() {
	}

	public Object getAnswerID() {
		return AnswerID;
	}

	public void setAnswerID(int answerID) {
		AnswerID = answerID;
	}
}