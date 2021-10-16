package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="boba_tea")
public class BobaTeaModel implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Column(name="price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name="size", nullable = false)
    private Integer size;

    @NotNull
    @Column(name="ice_level", nullable = false)
    private Integer iceLevel;

    @NotNull
    @Column(name="sugar_level", nullable = false)
    private Integer sugarLevel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_topping", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToppingModel idTopping;
}