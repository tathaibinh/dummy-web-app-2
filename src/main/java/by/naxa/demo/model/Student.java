package by.naxa.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * POJO Student.
 * Created by phomal on 09.03.2015.
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"photo", "rates"})
@ToString(callSuper = true, exclude = {"photo", "rates"}, includeFieldNames = false)
@NamedQuery(name = "Student.findByTheStudentsName", query = "select s from Student s where s.name = ?1")
public @Data class Student extends AbstractNamedPersistable<Long> {

	private static final long serialVersionUID = 6396741385679089363L;

	@Lob
	@Column(name = "Photo")
	private byte[] photo;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "Gender")
	private Gender gender = Gender.UNKNOWN;

	/*
	 * There is no cascade option on an ElementCollection,
	 * the target objects are always persisted, merged, removed with their parent.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name="Rates",
			joinColumns = @JoinColumn(name = "student_id"))
	private Collection<Integer> rates = Collections.emptyList();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "Faculty_id",
			nullable = false)
	private Faculty faculty;

}
