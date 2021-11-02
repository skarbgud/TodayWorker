package com.workerholic.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.springframework.stereotype.Service;

import com.workerholic.utils.ConvertUtils;
import com.workerholic.utils.DateUtils;
import com.workerholic.utils.ElasticsearchConnect;
import com.workerholic.vo.ReplyVO;

@Service
public class ReplyService implements ReplyServiceIF {

	// es연결 정보
	ElasticsearchConnect connect = new ElasticsearchConnect("192.168.1.103", 9200);

	// Rest connection 설정
	private final RestHighLevelClient client = connect.getConnection();

	// 인덱스 name
	private String indexName = "board";

	@Override
	public boolean registReply(ReplyVO vo) throws Exception {
		// bno
		String bno = vo.getBno();

		// rno 고유 키 생성
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		vo.setRno(uuid);

		// 등록일
		vo.setRegDate(DateUtils.getDatetimeString());
		vo.setIsRecomment(false);

		// VO -> Map
		Map<String, Object> replyMap = ConvertUtils.convertToMap(vo);

		// singtoneMap 정의
		Map<String, Object> singletonMap = Collections.singletonMap("reply", replyMap);

		UpdateRequest request = new UpdateRequest(indexName, indexName + bno).script(new Script(ScriptType.INLINE,
				"painless", "if (ctx._source.reply == null) {ctx._source.reply=[]} ctx._source.reply.add(params.reply)",
				singletonMap));

		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

		RestStatus status = response.status();

		return status == RestStatus.OK ? true : false;
	}

	@Override
	public boolean updateReply(ReplyVO vo) throws Exception {
		// bno
		String bno = vo.getBno();
		vo.setRegDate(DateUtils.getDatetimeString());
		vo.setIsRecomment(false);

		Map<String, Object> replyMap = ConvertUtils.convertToMap(vo);

		Map<String, Object> singletonMap = Collections.singletonMap("reply", replyMap);

		// reply중에서 rno이 같은 reply의 내용을 수정
		UpdateRequest request = new UpdateRequest(indexName, indexName + bno)
				.script(new Script(ScriptType.INLINE, "painless",
						"for(int i = 0; i< ctx._source.reply.size(); i++) {"
								+ "if (ctx._source.reply[i].rno == params.reply.rno) {"
								+ "ctx._source.reply[i]=params.reply }}",
								singletonMap));

		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

		RestStatus status = response.status();

		return status == RestStatus.OK ? true : false;
	}

	@Override
	public boolean deleteReply(ReplyVO vo) throws Exception {
		// bno
		String bno = vo.getBno();

		Map<String, Object> replyMap = ConvertUtils.convertToMap(vo);

		Map<String, Object> singletonMap = Collections.singletonMap("reply", replyMap);

		// reply중에서 rno이 같은 reply를 삭제
		UpdateRequest request = new UpdateRequest(indexName, indexName + bno)
				.script(new Script(ScriptType.INLINE, "painless",
						"for (int i = 0; i < ctx._source.reply.size(); i++) {"
								+ "if (ctx._source.reply[i].rno == params.reply.rno) {"
								+ "ctx._source.reply.remove(i) }}",
						singletonMap));

		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

		RestStatus status = response.status();

		return status == RestStatus.OK ? true : false;
	}

}
