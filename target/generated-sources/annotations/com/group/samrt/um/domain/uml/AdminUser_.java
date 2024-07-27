package com.group.samrt.um.domain.uml;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AdminUser.class)
public abstract class AdminUser_ {

	public static volatile SingularAttribute<AdminUser, String> identifier;
	public static volatile SingularAttribute<AdminUser, String> address;
	public static volatile SingularAttribute<AdminUser, String> role;
	public static volatile SingularAttribute<AdminUser, String> updatedBy;
	public static volatile SingularAttribute<AdminUser, String> password;
	public static volatile SingularAttribute<AdminUser, Instant> createdDt;
	public static volatile SingularAttribute<AdminUser, String> phone;
	public static volatile SingularAttribute<AdminUser, String> repreFullName;
	public static volatile SingularAttribute<AdminUser, String> companyPhone;
	public static volatile SingularAttribute<AdminUser, String> createdBy;
	public static volatile SingularAttribute<AdminUser, String> companyAddress;
	public static volatile SingularAttribute<AdminUser, String> company;
	public static volatile SingularAttribute<AdminUser, Instant> updatedDt;
	public static volatile SingularAttribute<AdminUser, Long> id;
	public static volatile SingularAttribute<AdminUser, String> fullname;
	public static volatile SingularAttribute<AdminUser, String> email;
	public static volatile SingularAttribute<AdminUser, String> username;
	public static volatile SingularAttribute<AdminUser, String> status;

	public static final String IDENTIFIER = "identifier";
	public static final String ADDRESS = "address";
	public static final String ROLE = "role";
	public static final String UPDATED_BY = "updatedBy";
	public static final String PASSWORD = "password";
	public static final String CREATED_DT = "createdDt";
	public static final String PHONE = "phone";
	public static final String REPRE_FULL_NAME = "repreFullName";
	public static final String COMPANY_PHONE = "companyPhone";
	public static final String CREATED_BY = "createdBy";
	public static final String COMPANY_ADDRESS = "companyAddress";
	public static final String COMPANY = "company";
	public static final String UPDATED_DT = "updatedDt";
	public static final String ID = "id";
	public static final String FULLNAME = "fullname";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";
	public static final String STATUS = "status";

}

