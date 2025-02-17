package sos.chat_api.domain.community.entity;

import jakarta.persistence.*;
import lombok.*;
import sos.chat_api.domain.category.entity.Category;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private String name;

}
