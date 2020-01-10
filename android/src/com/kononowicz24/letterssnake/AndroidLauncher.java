package com.kononowicz24.letterssnake;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.EventsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.games.event.Event;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.kononowicz24.letterssnake.interfaces.Achievement;
import com.kononowicz24.letterssnake.interfaces.PlayServices;
import com.kononowicz24.letterssnake.interfaces.PrefTokens;
import com.kononowicz24.letterssnake.interfaces.PreferenceRetriever;

public class AndroidLauncher extends AndroidApplication implements PreferenceRetriever, PlayServices {
	//@Override
	//protected void onCreate (Bundle savedInstanceState) {
	//	super.onCreate(savedInstanceState);
	//	AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
	//	initialize(new LettersSnake(), config);
	//}


	private static final String AD_UNIT_ID = "ca-app-pub-6060245360846265/3704078585";
	protected AdView adView;
	protected View gameView;
	// Client used to sign in with Google APIs
	private GoogleSignInClient mGoogleSignInClient;

	// Client variables
	private AchievementsClient mAchievementsClient;
	private LeaderboardsClient mLeaderboardsClient;
	private EventsClient mEventsClient;
	private PlayersClient mPlayersClient;
	private GamesClient mGamesClient;
	// achievements and scores we're pending to push to the cloud
	// (waiting for the user to sign in, for instance)
	// request codes we use when invoking an external activity
	private static final int RC_UNUSED = 5001;
	private static final int RC_SIGN_IN = 22887777;
	// tag for debug logging
	private static final String TAG = "LSJPWP_GPLAY";
	private final AccomplishmentsOutbox mOutbox = new AccomplishmentsOutbox();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		//cfg.useGL20 = false;
		cfg.useAccelerometer = false;
		cfg.useCompass = false;

		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);

		AdView admobView = createAdView();
		layout.addView(admobView);
		View gameView = createGameView(cfg);
		layout.addView(gameView);

		setContentView(layout);
		startAdvertising(admobView);
		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());
	}

	private AdView createAdView() {
		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(AD_UNIT_ID);
		adView.setId(R.id.adId); // this is an arbitrary id, allows for relative positioning in createGameView()
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		adView.setLayoutParams(params);
		adView.setBackgroundColor(Color.BLACK);
		return adView;
	}

	private View createGameView(AndroidApplicationConfiguration cfg) {
		gameView = initializeForView(new com.kononowicz24.letterssnake.LettersSnake(this, this), cfg);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.BELOW, adView.getId());
		gameView.setLayoutParams(params);
		return gameView;
	}

	private void startAdvertising(AdView adView) {
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice("54455453ABD591700921047F922DB781")
				.addTestDevice("C5F18975E1FCEC4BABEBA9574C7A170B")
				.addTestDevice("C5666B932806BA599FC8F681BEACCE7D").build();
		adView.loadAd(adRequest);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) adView.resume();
	}

	@Override
	public void onPause() {
		if (adView != null) adView.pause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		if (adView != null) adView.destroy();
		super.onDestroy();
	}
    @Override
    public int getIntPreference(String key) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        //int defaultValue = getResources().getInteger(R.integer.saved_high_score_default);
        return sharedPref.getInt(key, 0);
        //return highScore;
    }
    @Override
    public void setIntPreference(String key, int value) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply(); //.commit() jak nie zadziala
    }

	@Override
	public void setStringPreference(PrefTokens token, String value) {
		Context context = getContext();
		SharedPreferences sharedPref = context.getSharedPreferences("prefsLS", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		switch (token) {
			//case PLAYERS_BUS: {editor.putString("players_bus",value); break;}

			default: {break;}
		}

		editor.apply();
		editor.commit();
	}

	@Override
	public String getStringPreference(PrefTokens token) {
		Context context = getContext();
		SharedPreferences sharedPref = context.getSharedPreferences("prefsLS", MODE_PRIVATE);
		String defaultValue = "";
		switch (token) {
			//case PLAYERS_BUS: return sharedPref.getString("players_bus", "bus0");
			default: return defaultValue;
		}
	}
	@Override
	public void signIn() {
		startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
		//pushAccomplishments();
	}

	@Override
	public void signOut() {
		if (!isSignedIn()) {
			Log.w(TAG, "signOut() called, but was not signed in!");
			return;
		}

		mGoogleSignInClient.signOut().addOnCompleteListener(this,
				new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						boolean successful = task.isSuccessful();
						Log.d(TAG, "signOut(): " + (successful ? "success" : "failed"));

						onDisconnected();
					}
				});
	}

	@Override
	public void rateGame() {
		String str = getResources().getString(R.string.playstore_link);
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void unlockAchievement(Achievement achievement) {
		switch (achievement) {
			case SEVENYROLD: {
				//mAchievementsClient.unlock(getString(R.string.achievement_you_have_the_driving_license));
				mOutbox.m7yrAchievement = true;
				break;
			}
		}
		pushAccomplishments();
	}

	@Override
	public void submitScore(long highScore) {
		//if (mOutbox.mHS>highScore) mOutbox.mHS = highScore;
		pushAccomplishments();
	}

	@Override
	public void submitTime(float time) {
		//if (mOutbox.mTime<time) mOutbox.mTime = time;
		pushAccomplishments();
	}

	@Override
	public void showAchievements() {
		if (isSignedIn())
			mAchievementsClient.getAchievementsIntent()
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_UNUSED);
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							handleException(e, getString(R.string.achievements_exception));
						}
					});
		//else signIn();
	}

	@Override
	public void showScore() {

		if (isSignedIn())
        /*mLeaderboardsClient.getAllLeaderboardsIntent()
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_UNUSED);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        handleException(e, getString(R.string.leaderboards_exception));
                    }
                });*/
			mLeaderboardsClient.getLeaderboardIntent(getString(R.string.leaderboard_alphabet_time_attack))
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_UNUSED);
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							handleException(e, getString(R.string.leaderboards_exception));
						}
					});
		//else signIn();
	}


	private void pushAccomplishments() {
		if (!isSignedIn()) {
			// can't push to the cloud, try again later
			return;
		}
		if (mOutbox.m7yrAchievement) {
			mAchievementsClient.unlock(getString(R.string.achievement_sevenyearold));
			mOutbox.m7yrAchievement = false;
		}

			//Toast.makeText(getContext(), "Updating the score", Toast.LENGTH_LONG).show();
			Log.w(TAG, "UPDATING SCORE");

	}

	@Override
	public boolean isSignedIn() {
		return (GoogleSignIn.getLastSignedInAccount(this) != null && mAchievementsClient!=null && mLeaderboardsClient!=null);
	}

	private void onDisconnected() {
		Log.d(TAG, "onDisconnected()");

		mAchievementsClient = null;
		mLeaderboardsClient = null;
		mPlayersClient = null;
		mGamesClient = null;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (requestCode == RC_SIGN_IN) {
			Task<GoogleSignInAccount> task =
					GoogleSignIn.getSignedInAccountFromIntent(intent);

			try {
				GoogleSignInAccount account = task.getResult(ApiException.class);
				onConnected(account);
			} catch (ApiException apiException) {
				String message = apiException.getMessage();
				if (message == null || message.isEmpty()) {
					message = getString(R.string.signin_other_error);
				}
				Log.d(TAG, "apiexception", apiException);
				onDisconnected();

				new AlertDialog.Builder(this)
						.setMessage(message)
						.setNeutralButton(android.R.string.ok, null)
						.show();
			}
		}
	}

	private void onConnected(GoogleSignInAccount googleSignInAccount) {
		Log.d(TAG, "onConnected(): connected to Google APIs");

		mAchievementsClient = Games.getAchievementsClient(this, googleSignInAccount);
		mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
		mEventsClient = Games.getEventsClient(this, googleSignInAccount);
		mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);
		mGamesClient = Games.getGamesClient(this, googleSignInAccount);
		mGamesClient.setViewForPopups(gameView);

		// Set the greeting appropriately on main menu
		mPlayersClient.getCurrentPlayer()
				.addOnCompleteListener(new OnCompleteListener<Player>() {
					@Override
					public void onComplete(@NonNull Task<Player> task) {
						String displayName;
						if (task.isSuccessful()) {
							displayName = task.getResult().getDisplayName();
						} else {
							Exception e = task.getException();
							handleException(e, getString(R.string.players_exception));
							displayName = "???";
						}
					}
				});
		// if we have accomplishments to push, push them
		if (!mOutbox.isEmpty()) {
			pushAccomplishments();
			Toast.makeText(this, getString(R.string.your_progress_will_be_uploaded),
					Toast.LENGTH_LONG).show();
		}

		loadAndPrintEvents();
	}

	private void signInSilently() {
		Log.d(TAG, "signInSilently()");

		mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
				new OnCompleteListener<GoogleSignInAccount>() {
					@Override
					public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
						if (task.isSuccessful()) {
							Log.d(TAG, "signInSilently(): success");
							onConnected(task.getResult());
						} else {
							Log.d(TAG, "signInSilently(): failure", task.getException());
							onDisconnected();
						}
					}
				});
	}

	private void loadAndPrintEvents() {

		final AndroidLauncher mainActivity = this;

		mEventsClient.load(true)
				.addOnSuccessListener(new OnSuccessListener<AnnotatedData<EventBuffer>>() {
					@Override
					public void onSuccess(AnnotatedData<EventBuffer> eventBufferAnnotatedData) {
						EventBuffer eventBuffer = eventBufferAnnotatedData.get();

						int count = 0;
						if (eventBuffer != null) {
							count = eventBuffer.getCount();
						}

						Log.i(TAG, "number of events: " + count);

						for (int i = 0; i < count; i++) {
							Event event = eventBuffer.get(i);
							Log.i(TAG, "event: "
									+ event.getName()
									+ " -> "
									+ event.getValue());
						}
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						handleException(e, getString(R.string.achievements_exception));
					}
				});
	}

	private void handleException(Exception e, String details) {
		int status = 0;

		if (e instanceof ApiException) {
			ApiException apiException = (ApiException) e;
			status = apiException.getStatusCode();
		}

		String message = getString(R.string.status_exception_error, details, status, e);

		new AlertDialog.Builder(AndroidLauncher.this)
				.setMessage(message)
				.setNeutralButton(android.R.string.ok, null)
				.show();
	}

	@TargetApi(19)
	public void hideVirtualButtons() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getWindow().getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LAYOUT_STABLE
								| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
								| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
								| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
								| View.SYSTEM_UI_FLAG_FULLSCREEN
								| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
			}
		});
	}

	@Override
	public int getVersionCode() {
		int versionNumber;
		try {
			PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			versionNumber = pinfo.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			versionNumber = -1;
			Log.w("BDR_V", "", e);
		}
		return versionNumber;
	}

	@Override
	public String getVersionName() {
		String versionName;
		try {
			PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			versionName = pinfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			versionName = getResources().getString(R.string.unknown);
			Log.w("BDR_V", "", e);
		}
		return versionName;
	}

	private class AccomplishmentsOutbox {
		boolean m7yrAchievement = false; //todo

		boolean isEmpty() {
			return  m7yrAchievement;
		}

	}
}
