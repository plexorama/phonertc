package com.dooble.phonertc;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class SessionConfig {
	private boolean _isInitiator;
	private String _turnServerHost;
	private String _turnServerUsername;
	private String _turnServerPassword;

  private String _stunServerHost;
  private String _stunServerUsername;
  private String _stunServerPassword;

	private boolean _audioStreamEnabled;
	private boolean _videoStreamEnabled;

	private JSONArray _mandatoryMediaConstraints;
	private JSONArray _optionalMediaConstraints;
  private String _preferredCodec;
	
	public String getTurnServerHost() {
		return _turnServerHost;
	}
	
	public void setTurnServerHost(String _turnServerHost) {
		this._turnServerHost = _turnServerHost;
	}

	public String getTurnServerUsername() {
		return _turnServerUsername;
	}

	public void setTurnServerUsername(String _turnServerUsername) {
		this._turnServerUsername = _turnServerUsername;
	}

	public String getTurnServerPassword() {
		return _turnServerPassword;
	}

	public void setTurnServerPassword(String _turnServerPassword) {
		this._turnServerPassword = _turnServerPassword;
	}


  public String getStunServerHost() {
    return _stunServerHost;
  }

  public void setStunServerHost(String _stunServerHost) {
    this._stunServerHost = _stunServerHost;
  }

  public String getStunServerUsername() {
    return _stunServerUsername;
  }

  public void setStunServerUsername(String _stunServerUsername) {
    this._stunServerUsername = _stunServerUsername;
  }

  public String getStunServerPassword() {
    return _stunServerPassword;
  }

  public void setStunServerPassword(String _stunServerPassword) {
    this._stunServerPassword = _stunServerPassword;
  }

	public boolean isInitiator() {
		return _isInitiator;
	}

	public void setInitiator(boolean _isInitiator) {
		this._isInitiator = _isInitiator;
	}
	
	public boolean isAudioStreamEnabled() {
		return _audioStreamEnabled;
	}

	public void setAudioStreamEnabled(boolean _audioStreamEnabled) {
		this._audioStreamEnabled = _audioStreamEnabled;
	}

	public boolean isVideoStreamEnabled() {
		return _videoStreamEnabled;
	}

	public void setVideoStreamEnabled(boolean _videoStreamEnabled) {
		this._videoStreamEnabled = _videoStreamEnabled;
	}

  public JSONArray getOptionalMediaConstraints() {
	  return this._optionalMediaConstraints;
	}

  public void setMandatoryMediaConstraints( JSONArray constraints ) {
    this._mandatoryMediaConstraints = constraints;
  }

  public void setOptionalMediaConstraints( JSONArray constraints ) {
    this._optionalMediaConstraints = constraints;
  }

  public JSONArray getMandatoryMediaConstraints() {
    return this._mandatoryMediaConstraints;
  }

	public String getPreferredCodec() {
		return this._preferredCodec;
	}

	public void setPreferredCodec(String _codec) {
		this._preferredCodec = _codec;
	}

	public static SessionConfig fromJSON(JSONObject json) throws JSONException {
		SessionConfig config = new SessionConfig();
		config.setInitiator(json.getBoolean("isInitiator"));
		
		JSONObject turn = json.getJSONObject("turn");
		config.setTurnServerHost(turn.getString("host"));
		config.setTurnServerUsername(turn.getString("username"));
		config.setTurnServerPassword(turn.getString("password"));

	  config.setPreferredCodec( json.optString("prefercodec") );	

    JSONObject stun = json.optJSONObject("stun");
		if (stun != null){
      config.setStunServerHost(stun.getString("host"));
      config.setStunServerUsername(stun.getString("username"));
      config.setStunServerPassword(stun.getString("password"));
    } else {
		  config.setStunServerHost("");
			config.setStunServerUsername("");
			config.setStunServerPassword("");
		}
		JSONObject streams = json.getJSONObject("streams");
		config.setAudioStreamEnabled(streams.getBoolean("audio"));
		config.setVideoStreamEnabled(streams.getBoolean("video"));

    JSONObject mc = json.optJSONObject("mediaconstraints");
		if (mc != null) {
	    config.setMandatoryMediaConstraints( mc.optJSONArray("mandatory") );
      config.setOptionalMediaConstraints( mc.optJSONArray("optional") );
		}
		return config;
	}
}
