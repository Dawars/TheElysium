package clickme.nocubes.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class RenderTweakInjector implements IClassTransformer {

    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null)
            return null;

        boolean obfuscated = true;

        if (!"ble".equals(name))
            if (!"net.minecraft.client.renderer.RenderBlocks".equals(name))
                return bytes;
            else
                obfuscated = false;

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, ClassReader.EXPAND_FRAMES);

        MethodNode targetMethod = null;
        for (MethodNode methodNode : classNode.methods)
            if (obfuscated) {
                if ("b".equals(methodNode.name) && "(Lahu;III)Z".equals(methodNode.desc)) {
                    targetMethod = methodNode;
                    break;
                }
            } else if ("renderBlockByRenderType".equals(methodNode.name)) {
                targetMethod = methodNode;
                break;
            }

        if (targetMethod == null)
            return bytes;

        MethodNode injectedMethod = new MethodNode();

        Label label0 = new Label();
        injectedMethod.visitLabel(label0);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 1);
        injectedMethod.visitMethodInsn(Opcodes.INVOKESTATIC, "clickme/nocubes/SoftBlockRenderer", "shouldHookRenderer",
                obfuscated ? "(Lahu;)Z" : "(Lnet/minecraft/block/Block;)Z");

        Label label1 = new Label();
        injectedMethod.visitJumpInsn(Opcodes.IFEQ, label1);

        Label label2 = new Label();
        injectedMethod.visitLabel(label2);
        injectedMethod.visitFieldInsn(Opcodes.GETSTATIC, "clickme/nocubes/NoCubes", "softBlockRenderer",
                "Lclickme/nocubes/SoftBlockRenderer;");
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 1);
        injectedMethod.visitVarInsn(Opcodes.ILOAD, 2);
        injectedMethod.visitVarInsn(Opcodes.ILOAD, 3);
        injectedMethod.visitVarInsn(Opcodes.ILOAD, 4);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 0);
        injectedMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "clickme/nocubes/SoftBlockRenderer", "directRenderHook",
                obfuscated ? "(Lahu;IIILble;)Z" : "(Lnet/minecraft/block/Block;III" + "Lnet/minecraft/client/renderer/"
                        + "RenderBlocks;)Z");
        injectedMethod.visitInsn(Opcodes.IRETURN);
        injectedMethod.visitLabel(label1);

        targetMethod.instructions.insert(injectedMethod.instructions);

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }

}