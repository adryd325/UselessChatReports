package com.adryd.uselesschatreports.mixin;

import net.minecraft.network.encryption.NetworkEncryptionUtils.SaltSupplier;
import net.minecraft.unmapped.C_byvkekfd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.Instant;
import java.util.UUID;

@Mixin(C_byvkekfd.class)
public class ChatMessageSignerMixin {
	/* loom mappings
	 * method_43866=create
	 * C_byvkekfd=ChatMessageSigner
	 */
	@Inject(method = "method_43866", at = @At("HEAD"), cancellable = true)
	private static void create(UUID sender, CallbackInfoReturnable<C_byvkekfd> cir) {
		long now = Instant.now().getEpochSecond();
		Instant randomTime = Instant.ofEpochSecond(((long) Math.floor(Math.random() * 180) - 90) + now);
		cir.setReturnValue(new C_byvkekfd(sender, randomTime, SaltSupplier.nextLong()));
	}
}
