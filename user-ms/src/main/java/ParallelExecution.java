import java.util.stream.IntStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.training.userms.model.ResponseDto;

public class ParallelExecution {
public static void main(String[] args) {
	String url = "http://localhost:8888/api/users/-4";
	RestTemplate req = new RestTemplate();
	IntStream.range(0, 6).parallel().forEach( x -> {
		ResponseEntity<ResponseDto> res =null;
		try {
		res = req.getForEntity(url, ResponseDto.class);
		}catch(Exception e) {
			System.out.println("********" +e.getMessage());
		}
		finally {
			if(res != null)
			System.out.println("Response code = "+res.getStatusCode());
		}
	});
}
}
