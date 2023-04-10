package com.bosch.model.dto;

import java.util.Objects;

public class SubscriptionDTO {
	
	private boolean active;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionDTO other = (SubscriptionDTO) obj;
		return active == other.active && Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "SubscriptionDTO [active=" + active + ", description=" + description + "]";
	}
}
