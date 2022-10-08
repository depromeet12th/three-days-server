package com.depromeet.threedays.data;

import java.lang.annotation.*;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
public @interface DomainTransactional {

	@AliasFor(annotation = Transactional.class)
	Propagation propagation() default Propagation.REQUIRED;

	@AliasFor(annotation = Transactional.class)
	Isolation isolation() default Isolation.DEFAULT;

	@AliasFor(annotation = Transactional.class)
	int timeout() default -1;

	@AliasFor(annotation = Transactional.class)
	Class<? extends Throwable>[] rollbackFor() default {};

	@AliasFor(annotation = Transactional.class)
	String[] rollbackForClassName() default {};

	@AliasFor(annotation = Transactional.class)
	Class<? extends Throwable>[] noRollbackFor() default {};

	@AliasFor(annotation = Transactional.class)
	String[] noRollbackForClassName() default {};

	@AliasFor(annotation = Transactional.class)
	boolean readOnly() default false;
}
