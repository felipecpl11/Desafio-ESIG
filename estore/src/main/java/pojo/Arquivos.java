package pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "content", "last", "totalElements", "totalPages", "size", "number", "sort", "first",
		"numberOfElements" })
public class Arquivos {

	@JsonProperty("content")
	private List<Content> content = null;
	@JsonProperty("last")
	private Boolean last;
	@JsonProperty("totalElements")
	private Integer totalElements;
	@JsonProperty("totalPages")
	private Integer totalPages;
	@JsonProperty("size")
	private Integer size;
	@JsonProperty("number")
	private Integer number;
	@JsonProperty("sort")
	private Object sort;
	@JsonProperty("first")
	private Boolean first;
	@JsonProperty("numberOfElements")
	private Integer numberOfElements;

	@JsonProperty("content")
	public List<Content> getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(List<Content> content) {
		this.content = content;
	}

	@JsonProperty("last")
	public Boolean getLast() {
		return last;
	}

	@JsonProperty("last")
	public void setLast(Boolean last) {
		this.last = last;
	}

	@JsonProperty("totalElements")
	public Integer getTotalElements() {
		return totalElements;
	}

	@JsonProperty("totalElements")
	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	@JsonProperty("totalPages")
	public Integer getTotalPages() {
		return totalPages;
	}

	@JsonProperty("totalPages")
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	@JsonProperty("size")
	public Integer getSize() {
		return size;
	}

	@JsonProperty("size")
	public void setSize(Integer size) {
		this.size = size;
	}

	@JsonProperty("number")
	public Integer getNumber() {
		return number;
	}

	@JsonProperty("number")
	public void setNumber(Integer number) {
		this.number = number;
	}

	@JsonProperty("sort")
	public Object getSort() {
		return sort;
	}

	@JsonProperty("sort")
	public void setSort(Object sort) {
		this.sort = sort;
	}

	@JsonProperty("first")
	public Boolean getFirst() {
		return first;
	}

	@JsonProperty("first")
	public void setFirst(Boolean first) {
		this.first = first;
	}

	@JsonProperty("numberOfElements")
	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	@JsonProperty("numberOfElements")
	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("content", content).append("last", last)
				.append("totalElements", totalElements).append("totalPages", totalPages).append("size", size)
				.append("number", number).append("sort", sort).append("first", first)
				.append("numberOfElements", numberOfElements).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(content).append(numberOfElements).append(sort).append(last)
				.append(totalElements).append(number).append(first).append(totalPages).append(size).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Arquivos) == false) {
			return false;
		}
		Arquivos rhs = ((Arquivos) other);
		return new EqualsBuilder().append(content, rhs.content).append(numberOfElements, rhs.numberOfElements)
				.append(sort, rhs.sort).append(last, rhs.last).append(totalElements, rhs.totalElements)
				.append(number, rhs.number).append(first, rhs.first).append(totalPages, rhs.totalPages)
				.append(size, rhs.size).isEquals();
	}

}