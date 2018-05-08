/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.graph;

import com.arcadedb.database.RID;

public interface VertexInternal extends Vertex {
  RID getOutEdgesHeadChunk();

  RID getInEdgesHeadChunk();

  void setOutEdgesHeadChunk(RID newChunk);

  void setInEdgesHeadChunk(RID newChunk);
}
