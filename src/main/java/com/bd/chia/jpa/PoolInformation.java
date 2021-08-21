package com.bd.chia.jpa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bd.chia.utils.Constants;

@Document
public class PoolInformation {
	public static final String ID = "ONLY_ONE";
	
	public static class PoolSpaceTime {
		private BigDecimal poolSpaceRaw;
		private Double poolSpace;
		private String poolSpaceUnit;
		private Date date;
		
		public BigDecimal getPoolSpaceRaw() {
			return poolSpaceRaw;
		}
		public void setPoolSpaceRaw(BigDecimal poolSpaceRaw) {
			this.poolSpaceRaw = poolSpaceRaw;
		}
		public Double getPoolSpace() {
			return poolSpace;
		}
		public void setPoolSpace(Double poolSpace) {
			this.poolSpace = poolSpace;
		}
		public String getPoolSpaceUnit() {
			return poolSpaceUnit;
		}
		public void setPoolSpaceUnit(String poolSpaceUnit) {
			this.poolSpaceUnit = poolSpaceUnit;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
	}
	
	@Id
	private String id;
	
	private BigDecimal netspaceRaw;
	private Double netspace;
	private String netspaceUnit;
	private Integer netDifficulty;
	private Long farmerOnline;
	private Integer blockFound;
	private BigDecimal poolSpaceRaw;
	private Double poolSpace;
	private String poolSpaceUnit;
	private Long poolTotalPoints;
	private Date updateTime;
	private List<PoolSpaceTime> poolSpaceHistories;
	
	public PoolInformation() {
		id = ID;
	}

	public String getId() {
		return id;
	}

	
	public BigDecimal getNetspaceRaw() {
		return netspaceRaw;
	}

	public void setNetspaceRaw(BigDecimal netspaceRaw) {
		if(netspaceRaw.compareTo(Constants.YiB) > 0) {
			netspace = netspaceRaw.divide(Constants.YiB).setScale(2, RoundingMode.CEILING).doubleValue();
			netspaceUnit = "YiB";
		} else if(netspaceRaw.compareTo(Constants.ZiB) > 0) {
			netspace = netspaceRaw.divide(Constants.ZiB).setScale(2, RoundingMode.CEILING).doubleValue();
			netspaceUnit = "ZiB";
		} else if(netspaceRaw.compareTo(Constants.EiB) > 0) {
			netspace = netspaceRaw.divide(Constants.EiB).setScale(2, RoundingMode.CEILING).doubleValue();
			netspaceUnit = "EiB";
		} else if(netspaceRaw.compareTo(Constants.PiB) > 0) {
			netspace = netspaceRaw.divide(Constants.PiB).setScale(2, RoundingMode.CEILING).doubleValue();
			netspaceUnit = "PiB";
		} else if(netspaceRaw.compareTo(Constants.TiB) > 0) {
			netspace = netspaceRaw.divide(Constants.TiB).setScale(2, RoundingMode.CEILING).doubleValue();
			netspaceUnit = "TiB";
		} else if(netspaceRaw.compareTo(Constants.GiB) > 0) {
			netspace = netspaceRaw.divide(Constants.GiB).setScale(2, RoundingMode.CEILING).doubleValue();
			netspaceUnit = "GiB";
		}
		this.netspaceRaw = netspaceRaw;
	}

	public void setNetspace(Double netspace) {
		this.netspace = netspace;
	}

	public Double getNetspace() {
		return netspace;
	}

	public String getNetspaceUnit() {
		return netspaceUnit;
	}

	public void setNetspaceUnit(String netspaceUnit) {
		this.netspaceUnit = netspaceUnit;
	}

	public Integer getNetDifficulty() {
		return netDifficulty;
	}

	public void setNetDifficulty(Integer netDifficulty) {
		this.netDifficulty = netDifficulty;
	}

	public Long getFarmerOnline() {
		return farmerOnline;
	}

	public void setFarmerOnline(Long farmerOnline) {
		this.farmerOnline = farmerOnline;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getBlockFound() {
		return blockFound;
	}

	public void setBlockFound(Integer blockFound) {
		this.blockFound = blockFound;
	}

	public BigDecimal getPoolSpaceRaw() {
		return poolSpaceRaw;
	}

	public void setPoolSpaceRaw(BigDecimal poolSpaceRaw) {				
		if(poolSpaceRaw.compareTo(Constants.YiB) > 0) {
			poolSpace = poolSpaceRaw.divide(Constants.YiB).setScale(2, RoundingMode.CEILING).doubleValue();
			poolSpaceUnit = "YiB";
		} else if(poolSpaceRaw.compareTo(Constants.ZiB) > 0) {
			poolSpace = poolSpaceRaw.divide(Constants.ZiB).setScale(2, RoundingMode.CEILING).doubleValue();
			poolSpaceUnit = "ZiB";
		} else if(poolSpaceRaw.compareTo(Constants.EiB) > 0) {
			poolSpace = poolSpaceRaw.divide(Constants.EiB).setScale(2, RoundingMode.CEILING).doubleValue();
			poolSpaceUnit = "EiB";
		} else if(poolSpaceRaw.compareTo(Constants.PiB) > 0) {
			poolSpace = poolSpaceRaw.divide(Constants.PiB).setScale(2, RoundingMode.CEILING).doubleValue();
			poolSpaceUnit = "PiB";
		} else if(poolSpaceRaw.compareTo(Constants.TiB) > 0) {
			poolSpace = poolSpaceRaw.divide(Constants.TiB).setScale(2, RoundingMode.CEILING).doubleValue();
			poolSpaceUnit = "TiB";
		} else if(poolSpaceRaw.compareTo(Constants.GiB) > 0) {
			poolSpace = poolSpaceRaw.divide(Constants.GiB).setScale(2, RoundingMode.CEILING).doubleValue();
			poolSpaceUnit = "GiB";
		}
		this.poolSpaceRaw = poolSpaceRaw;
	}

	public void setPoolSpace(Double poolSpace) {
		this.poolSpace = poolSpace;
	}
	public Double getPoolSpace() {
		return poolSpace;
	}
	public String getPoolSpaceUnit() {
		return poolSpaceUnit;
	}

	public void setPoolSpaceUnit(String poolSpaceUnit) {
		this.poolSpaceUnit = poolSpaceUnit;
	}

	public Long getPoolTotalPoints() {
		return poolTotalPoints;
	}

	public void setPoolTotalPoints(Long poolTotalPoints) {
		this.poolTotalPoints = poolTotalPoints;
	}

	public List<PoolSpaceTime> getPoolSpaceHistories() {
		return poolSpaceHistories;
	}

	public void setPoolSpaceHistories(List<PoolSpaceTime> poolSpaceHistories) {
		this.poolSpaceHistories = poolSpaceHistories;
	}
	
	public void addPoolSpaceHistory(PoolSpaceTime poolSpaceTime) {
		if(poolSpaceHistories==null) {
			poolSpaceHistories = new ArrayList<PoolSpaceTime>();
		}
		
		poolSpaceHistories.add(poolSpaceTime);
	}
}
