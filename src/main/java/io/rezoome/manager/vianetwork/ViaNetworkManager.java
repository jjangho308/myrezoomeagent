package io.rezoome.manager.vianetwork;

import java.util.List;

import io.rezoome.external.entity.AgencyResultEntity;
import io.rezoome.manager.Manager;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public interface ViaNetworkManager extends Manager{
  public List<AgencyResultEntity> request(ViaRequestPacketEntity packet, AgencyResultEntity agencyResultArgs);
}
