package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	public List<Reply> getForPrintReplies(String relTypeCode, int relId);

	public void doWrite(int memberId, int relId, String relTypeCode, String body);

	public int getLastInsertId();

	public Reply getReplyById(int id);

	public void deleteReply(int id);

	public void modifyReply(int id, String body);

}
