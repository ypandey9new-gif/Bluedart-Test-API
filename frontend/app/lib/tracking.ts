export async function trackShipment(awb: string) {
  const res = await fetch(`https://musical-meme-wrpww4pgjq6639x99-8080.app.github.dev/api/tracking/${awb}`);
  if (!res.ok) throw new Error("Tracking failed");
  return await res.json(); // JSON now
}
