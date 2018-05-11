package entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 * 
 * @author zwp
 *
 * @param <T>
 */
public class PageResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long total;

	private List rows;

	public PageResult(Long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
