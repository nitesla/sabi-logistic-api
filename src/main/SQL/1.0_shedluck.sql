CREATE TABLE shedlock (
  name VARCHAR(64),
  lock_until TIMESTAMP(3) NULL,
  locked_at TIMESTAMP(3) NULL,
  locked_by VARCHAR(255),
  PRIMARY KEY (name)
)


CREATE  PROCEDURE `GetTrips`()
BEGIN

SELECT partnerId,
COUNT(deliveryStatus) as completedTrips,
SUM(earnings) as totalEarnings ,
date ,
partnerAssetTypeId
FROM sabilogistic.dashboardsummary AS d
INNER JOIN `partnerasset` AS p ON d.assetTypeId
where deliveryStatus='pending'
  GROUP by partnerId,date;


END