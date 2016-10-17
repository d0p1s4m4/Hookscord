# Hookscord
Just a simple java api for discord webhooks

## Usage

```java
Hookscord hk = new Hookscord("https://canary.discordapp.com/api/webhooks/XXXX");
Message msg = new Message("Trekkie Monster");
msg.setText("Internet is for porn");
hk.sendMessage(msg);
```

## Thanks to:

* [Dakurei](https://twitter.com/Dakurei_PVT) : name and charset fix
