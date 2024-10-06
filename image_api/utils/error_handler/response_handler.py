from api.data.general import BaseResponse
from utils.constants.collection_name import CollectionName
from utils.database.database import Database
from utils.error_handler.error_codes import ErrorCode
from utils.error_handler.error_data import CustomError


def return_error_message(
        db: Database,
        error: ErrorCode
) -> BaseResponse[dict]:
    error_objs = db.get_object(
        CollectionName.ERROR_MESSAGES.value,
        {
            "error_code": error.value
        }
    )
    # TODO add default error message
    if len(error_objs) == 0 or error_objs is None:
        custom_error_default_data = {
            "error_message": "Please contact with NexArb team",
            "go_back": False,
            "go_home": False,
            "logout": False
        }
        return BaseResponse[dict](error=True, data={"data": CustomError(**custom_error_default_data)},error_code=404)
    return BaseResponse[dict](error=True, data={"data": CustomError(**error_objs[0])}, error_code=error.value)
