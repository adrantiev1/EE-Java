package dmit2015.oe.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.text.ParseException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Components.ForEach;
import org.omnifaces.util.Messages;

import dmit2015.oe.entity.Category;
import dmit2015.oe.entity.Customer;
import dmit2015.oe.entity.Order;
import dmit2015.oe.entity.ProductDescription;
import dmit2015.oe.entity.ProductDescriptionPK;
import dmit2015.oe.entity.ProductInformation;
import dmit2015.oe.report.CategorySales;
import dmit2015.oe.report.ProductSales;


@Stateless
public class OrderEntryService {

	@Inject
	private EntityManager entityManager;
	
	public Order findOneOrder(long orderId) {
		Order querySingleResult = null;
		try {
			querySingleResult = entityManager.createQuery("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.orderId = :orderIdValue",
					Order.class)
					.setParameter("orderIdValue", orderId)
					.getSingleResult();
			
		} catch (NoResultException e) {
			querySingleResult = null;
		}
		return querySingleResult;
		
	}
	
	public List<Order> findAllOrderByDateRange(Date startDate, Date endDate) {
		// TODO: Complete the code for this method
		SimpleDateFormat newSdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			startDate = newSdf.parse(newSdf.format(startDate));
			Calendar newCalendar = Calendar.getInstance();
			newCalendar.set(Calendar.HOUR_OF_DAY,23);
			newCalendar.set(Calendar.MINUTE,59);
			endDate = newCalendar.getTime();
			
		} catch (ParseException err) {
			Messages.addGlobalError(err.getMessage());
		}
		
		
		List<Order> queryResult = null;
		try {
			queryResult = entityManager.createQuery("SELECT o FROM Order o WHERE o.orderDate BETWEEN  :orderstartDate AND :orderEndDate", Order.class)
					.setParameter("orderstartDate",startDate)
					.setParameter("orderEndDate", endDate)
					.getResultList();
		} catch (Exception e) {
			queryResult = null;
		}
		
		return queryResult;
	}
		
	public List<Order> findAllOrderByCustomerId(Long customerId) {
		Customer cust = entityManager.find(Customer.class,customerId);
		List<Order> queryResult = null;
		try {
			queryResult =  entityManager.createQuery(
					"SELECT o FROM Order o WHERE o.customer = :customerIdValue", 
					Order.class)
					.setParameter("customerIdValue", cust)
					.getResultList();
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
			queryResult = null;
		}
		return queryResult;
	}
	
	
	public Customer findOneCustomer(long customerId) {
		return entityManager.find(Customer.class, customerId);
	}
	
	public Customer findOneCustomerByUniqueValue(String queryValue) { 
		// TODO: Complete the code for this method
		Customer querySingleResult = null;
		try {	
			try {
				
				querySingleResult = entityManager.createQuery(
						"SELECT c FROM Customer c WHERE c.custEmail = :customerEmailValue", 
						Customer.class)
						.setParameter("customerEmailValue", queryValue)
						.getSingleResult();
			
				
			} catch (Exception e) {
				querySingleResult = null;
				
			}	
			
			try {
				if (querySingleResult == null) {
					querySingleResult = entityManager.createQuery(
							"SELECT c FROM Customer c WHERE c.customerId = :customerIdValue", 
							Customer.class)
							.setParameter("customerIdValue", Long.parseLong(queryValue))
							.getSingleResult();
				}
				
			} catch (NumberFormatException e) {
				querySingleResult = null;
				
			}
			try {
				if (querySingleResult == null) {
					querySingleResult = (Customer)entityManager.createNativeQuery(
							"SELECT * FROM CUSTOMERS c, TABLE(c.PHONE_NUMBERS) p WHERE p.Column_Value = :phoneValue", Customer.class)
							.setParameter("phoneValue", queryValue)
							.getSingleResult();
				}
				
			} catch (Exception e) {
				querySingleResult = null;
			}
			
		} catch(NoResultException e) {
			querySingleResult = null;
		}
		return querySingleResult; 
		
		
	}
	
	
	public List<ProductInformation> findAllProductInformationByPattern(String pattern) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public ProductDescription findOneProductDescription(Long productId, String languageId) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public ProductInformation findOneProductInformation(long productId) {
		return entityManager.find(ProductInformation.class, productId);
	}
	
	
	public Category findOneCategory(long categoryId) {
		return entityManager.find(Category.class, categoryId);
	}

	public List<Integer> findYearsWithOrders() {
		return entityManager.createQuery(
				"SELECT YEAR(o.orderDate) "
				+ "FROM Order o "
				+ "WHERE YEAR(o.orderDate) IS NOT NULL "
				+ "GROUP BY YEAR(o.orderDate) "
				+ "ORDER BY YEAR(o.orderDate) "
				, Integer.class)
				.getResultList();
	}
	
	public List<Category> findOnlineCatalogCategories() {
		return entityManager.createQuery("SELECT c FROM Category c "
				+ "WHERE c.categoryId = 90 OR c.categoryId = 10 OR c.categoryId = 20 OR c.categoryId = 30"
				+ "ORDER BY c.categoryName", Category.class).getResultList();
	}
	
	public List<CategorySales> findCategorSalesForOnlineCatalog() {
		return entityManager.createQuery(
				"SELECT new dmit2015.oe.report.CategorySales(c.categoryName, SUM(oi.unitPrice * oi.quantity) ) "
				+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (oi.order) o "
				+ " GROUP BY c.categoryId",
				CategorySales.class)
				.getResultList();

	}
	
	public List<CategorySales> findCategorSalesForOnlineCatalogYear(Integer year) {
		if (year == null) {
			return findCategorSalesForOnlineCatalog();
		}
		return entityManager.createQuery(
			"SELECT new dmit2015.oe.report.CategorySales(c.categoryName, SUM(oi.unitPrice * oi.quantity)) "
			+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (oi.order) o "
			+ " WHERE YEAR(o.orderDate) = :yearValue "
			+ " GROUP BY c.categoryId",
			CategorySales.class)
			.setParameter("yearValue", year)
			.getResultList();
	}
	
	public List<CategorySales> findCategorSalesForParentCategoryId(Long parentCategoryId) {
		return entityManager.createQuery(
				"SELECT new dmit2015.oe.report.CategorySales(c.categoryName, SUM(oi.unitPrice * oi.quantity)) "
				+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (oi.order) o "
				+ " WHERE c.categoryId = :parentCatID "
				+ " GROUP BY c.categoryId",
				CategorySales.class)
				.setParameter("parentCatID", parentCategoryId )
				.getResultList();
	}
	
	public List<CategorySales> findCategorSalesForParentCategoryIdAndYear(Long parentCategoryId, Integer year) {
		if (year == null) {
			return findCategorSalesForParentCategoryId(parentCategoryId);
		}
		return entityManager.createQuery(
				"SELECT new dmit2015.oe.report.CategorySales(c.categoryName, SUM(oi.unitPrice * oi.quantity)) "
				+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (oi.order) o "
				+ " WHERE c.categoryId = :parentCatID  AND YEAR(o.orderDate) = :yearValue "
				+ " GROUP BY c.categoryId",
				CategorySales.class)
				.setParameter("parentCatID", parentCategoryId)
				.setParameter("yearValue", year)
				.getResultList();
		
	}
		
	public List<ProductSales> findProductSales(int maxResult) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public List<ProductSales> findProductSalesForYear(Integer year, int maxResult) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
}
