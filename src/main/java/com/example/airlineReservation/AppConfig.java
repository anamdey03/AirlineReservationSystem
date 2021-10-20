package com.example.airlineReservation;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.airlineReservation.repository.AirlineReservationRepository;
import com.example.airlineReservation.repository.AirlineReservationRepositoryImpl;
import com.example.airlineReservation.repository.PassengerRepository;
import com.example.airlineReservation.repository.PassengerRepositoryImpl;
import com.example.airlineReservation.service.AirlineReservationService;
import com.example.airlineReservation.service.AirlineReservationServiceImpl;
import com.example.airlineReservation.service.PassengerService;
import com.example.airlineReservation.service.PassengerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class AppConfig extends CachingConfigurerSupport {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public AirlineReservationService airlineReservationService() {
		return new AirlineReservationServiceImpl();
	}
	
	@Bean PassengerService passengerService() {
		return new PassengerServiceImpl();
	}
	
	@Bean
	public AirlineReservationRepository airlineReservationRepository() {
		return new AirlineReservationRepositoryImpl(); 
	}
	
	@Bean
	public PassengerRepository passengerRepository() {
		return new PassengerRepositoryImpl();
	}
	
//	@Bean
//	public net.sf.ehcache.CacheManager ehCacheManager() {
//		CacheConfiguration tenSecondCache = new CacheConfiguration();
//		tenSecondCache.setName("ten-second-cache");
//		tenSecondCache.setMemoryStoreEvictionPolicy("LRU");
//		tenSecondCache.setMaxEntriesLocalHeap(1000);
//		tenSecondCache.setTimeToLiveSeconds(10);
//		
//		CacheConfiguration twentySecondCache = new CacheConfiguration();
//		twentySecondCache.setName("twenty-second-cache");
//		twentySecondCache.setMemoryStoreEvictionPolicy("LRU");
//		twentySecondCache.setMaxEntriesLocalHeap(1000);
//		twentySecondCache.setTimeToLiveSeconds(20);
//		
//		net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
//		configuration.addCache(tenSecondCache);
//		configuration.addCache(twentySecondCache);
//		
//		return net.sf.ehcache.CacheManager.newInstance(configuration);		
//	}
//	
//	@Override
//	@Bean
//	public CacheManager cacheManager() {
//		return new EhCacheCacheManager(ehCacheManager());
//	}
	
	@Bean
	public EhCacheManagerFactoryBean cacheManagerFactory() {
		EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
		bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		bean.setShared(true);
		return bean;
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(cacheManagerFactory().getObject());
	}
}
