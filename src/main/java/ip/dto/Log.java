package ip.dto;

import java.io.Serializable;
import java.util.Objects;

public class Log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5393645615228594889L;
	private Integer id;
	private String content;
	private String dateTime;
	
	public Log() {
		// TODO Auto-generated constructor stub
	}

	public Log(Integer id, String content, String dateTime) {
		super();
		this.id = id;
		this.content = content;
		this.dateTime = dateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, dateTime, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		return Objects.equals(content, other.content) && Objects.equals(dateTime, other.dateTime)
				&& Objects.equals(id, other.id);
	}

	
}
