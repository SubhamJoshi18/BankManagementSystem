package dto;

public class AddressDTO {


    private Integer id;


    private String name;

    private String type;

    private Integer studentid;

    public AddressDTO(Integer id, String name, String type, Integer studentid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.studentid = studentid;
    }

    public AddressDTO(String name, String type, Integer studentid){
        this.name = name;
        this.type = type;
        this.studentid =studentid;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }
}
