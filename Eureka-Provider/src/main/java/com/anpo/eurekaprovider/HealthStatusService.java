package com.anpo.eurekaprovider;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class HealthStatusService implements HealthIndicator{

	//自身的健康状态
	private Boolean status = true;

	@Override
	public Health health() {
		if(status) {
			return new Health.Builder().up().build();
		}
		return new Health.Builder().down().build();
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String isStatus() {
		return this.status.toString();
	}

	
}
