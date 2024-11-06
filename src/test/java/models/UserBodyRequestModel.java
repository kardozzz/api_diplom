package models;

import lombok.Data;

@Data
public class UserBodyRequestModel {
    String name;
    String job;
    String updatedAt;
    String createdAt;
    String id;

}
