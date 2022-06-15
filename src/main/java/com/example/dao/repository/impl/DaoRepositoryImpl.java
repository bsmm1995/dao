package com.example.dao.repository.impl;

import com.example.dao.domain.Person;
import com.example.dao.domain.dto.PersonDto;
import com.example.dao.repository.DaoRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class DaoRepositoryImpl implements DaoRepository {
  private final EntityManager entityManager;

  @Override
  public List<Person> findAll() {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT * FROM PERSON ");
    Query query = entityManager.createNativeQuery(sql.toString(), Person.class);
    return query.getResultList();
  }

  @Override
  public List<Person> findAllCriteria() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
    Root<Person> from = criteriaQuery.from(Person.class);
    CriteriaQuery<Person> select = criteriaQuery.select(from);
    TypedQuery<Person> typedQuery = entityManager.createQuery(select);
    return typedQuery.getResultList();
  }

  @Override
  public List<PersonDto> findAllCriteriaDto() {
    Session session = entityManager.unwrap(Session.class);
    Criteria cr =
        session
            .createCriteria(Person.class)
            .setProjection(
                Projections.projectionList()
                    .add(Projections.property("id"), "id")
                    .add(Projections.property("dni"), "dni")
                    .add(Projections.property("name"), "name")
                    .add(Projections.property("lastname"), "lastname"))
            .setResultTransformer(Transformers.aliasToBean(PersonDto.class));
    return cr.list();
  }

  @Override
  public List getReport(Date startDate, Date endDate) {
    StringBuilder sql = new StringBuilder();
    sql.append(
            "SELECT PER.DNI, CONCAT(PER.NAME, ' - ', PER.LASTNAME) AS FULL_NAMES, PVA.DATE, PVA.DOSE, VAC.NAME, VAC.LOT ")
        .append("FROM PERSON PER ")
        .append("INNER JOIN PERSON_VACCINE PVA ON PVA.PERSON_ID = PER.ID ")
        .append("INNER JOIN VACCINE VAC ON VAC.ID = PVA.VACCINE_ID ")
        .append("WHERE ")
        .append("PVA.DATE BETWEEN :startDate AND :endDate ")
        .append("ORDER BY DATE ");

    Query query = entityManager.createNativeQuery(sql.toString());
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);
    return query.getResultList();
  }
}
