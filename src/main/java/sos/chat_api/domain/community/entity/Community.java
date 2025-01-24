package sos.chat_api.domain.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sos.chat_api.domain.category.entity.Category;

@Entity
@Getter
@Setter
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_id;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private String community_name;

}
