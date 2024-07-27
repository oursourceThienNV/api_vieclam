package com.group.samrt.um.domain.uml;

import java.math.BigDecimal;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> addressProduct;
	public static volatile SingularAttribute<Product, String> address;
	public static volatile SingularAttribute<Product, String> updatedBy;
	public static volatile SingularAttribute<Product, BigDecimal> feeTotal;
	public static volatile SingularAttribute<Product, String> pname;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, String> decisionName;
	public static volatile SingularAttribute<Product, Instant> decisionDate;
	public static volatile SingularAttribute<Product, String> pno;
	public static volatile SingularAttribute<Product, Instant> createdDt;
	public static volatile SingularAttribute<Product, String> phone;
	public static volatile SingularAttribute<Product, String> createdBy;
	public static volatile SingularAttribute<Product, String> decisionNo;
	public static volatile SingularAttribute<Product, String> decisionAgency;
	public static volatile SingularAttribute<Product, Instant> updatedDt;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SingularAttribute<Product, String> email;
	public static volatile SingularAttribute<Product, String> status;

	public static final String ADDRESS_PRODUCT = "addressProduct";
	public static final String ADDRESS = "address";
	public static final String UPDATED_BY = "updatedBy";
	public static final String FEE_TOTAL = "feeTotal";
	public static final String PNAME = "pname";
	public static final String DESCRIPTION = "description";
	public static final String DECISION_NAME = "decisionName";
	public static final String DECISION_DATE = "decisionDate";
	public static final String PNO = "pno";
	public static final String CREATED_DT = "createdDt";
	public static final String PHONE = "phone";
	public static final String CREATED_BY = "createdBy";
	public static final String DECISION_NO = "decisionNo";
	public static final String DECISION_AGENCY = "decisionAgency";
	public static final String UPDATED_DT = "updatedDt";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";

}

