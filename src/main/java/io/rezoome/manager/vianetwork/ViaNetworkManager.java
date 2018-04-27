package io.rezoome.manager.vianetwork;

import io.rezoome.manager.Manager;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public interface ViaNetworkManager extends Manager{
  public ViaResponsePacketEntity request(ViaRequestPacketEntity packet, ViaResponsePacketEntity agencyResultArgs);
}
