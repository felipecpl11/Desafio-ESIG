package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "file_name", "content", "extension", "created", "author", "description", "path" })
public class Arquivo {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("file_name")
	private String fileName;
	@JsonProperty("content")
	private String content;
	@JsonProperty("extension")
	private String extension;
	@JsonProperty("created")
	private String created;
	@JsonProperty("author")
	private String author;
	@JsonProperty("description")
	private String description;
	@JsonProperty("path")
	private String path;
	@JsonProperty("usuarioLogin")
	private String usuarioLogin;

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("file_name")
	public String getFileName() {
		return fileName;
	}

	@JsonProperty("file_name")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonProperty("content")
	public String getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}

	@JsonProperty("extension")
	public String getExtension() {
		return extension;
	}

	@JsonProperty("extension")
	public void setExtension(String extension) {
		this.extension = extension;
	}

	@JsonProperty("created")
	public String getCreated() {
		return created;
	}

	@JsonProperty("created")
	public void setCreated(String created) {
		this.created = created;
	}

	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("path")
	public String getPath() {
		return path;
	}

	@JsonProperty("path")
	public void setPath(String path) {
		this.path = path;
	}

	@JsonProperty("usuarioLogin")
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	
	@JsonProperty("usuarioLogin")
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("fileName", fileName).append("content", content)
				.append("extension", extension).append("created", created).append("author", author)
				.append("description", description).append("path", path).append("usuarioLogin", usuarioLogin).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(extension).append(content).append(id).append(author).append(created)
				.append(description).append(path).append(fileName).append(usuarioLogin).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Arquivo) == false) {
			return false;
		}
		Arquivo rhs = ((Arquivo) other);
		return new EqualsBuilder().append(extension, rhs.extension).append(content, rhs.content).append(id, rhs.id)
				.append(author, rhs.author).append(created, rhs.created).append(description, rhs.description)
				.append(path, rhs.path).append(fileName, rhs.fileName).append("usuarioLogin", rhs.usuarioLogin).isEquals();
	}

}