from fastapi import APIRouter
import torch
from io import BytesIO
from PIL import Image
import base64
from diffusers import DiffusionPipeline,PixArtAlphaPipeline
from transformers import T5EncoderModel
router = APIRouter(tags=["Image"], prefix="/image")


@router.get("/{text}",response_model=str)
def generate_image(text:str):
    pipeline = DiffusionPipeline.from_pretrained("dreamlike-art/dreamlike-photoreal-2.0")
    if torch.cuda.is_available():
        pipeline = pipeline.to("cuda")
    image = pipeline(
        text,
        low_cpu_mem_usage=True,
        negative_prompt="",
        num_inference_steps=7,
        guidance_scale=7.0,
    ).images[0]
    buffered = BytesIO()
    image.save(buffered, format="JPEG")
    buffered.seek(0)

    return base64.b64encode(buffered.getvalue()).decode("utf-8")
