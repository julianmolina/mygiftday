<?php

/**
 * 
 */
class IndexController extends ControllerBase
{
    public function indexAction()
    {
		$array = array();
		foreach (Bonus::find('amount = 5000') as $item) { 
			$array[] = array(
				'amount' => $item->amount
			);
		}
		echo json_encode($array);
    }
	
	public function eventosAction()
	{
		$array = array();
		foreach (EventType::find() as $item) { 
			$array[] = array(
				'id_event_type' => $item->id_event_type,
				'event_type' => $item->event_type
			);
		}
		echo json_encode($array);
	}

	public function InsertAction()
	{
		$user = new User();
		$info = array();
		$info = $this->request->getJsonRawBody();
		
		$checkUser = User::findFirst("id_facebook = ".$info->id_facebook);
		if ($checkUser == false) {
			$user->id_facebook 	= $info->id_facebook;
			$user->name			= $info->name;
			$user->last			= $info->last;
			$user->email		= $info->email;
			$user->birthday		= $info->birthday;
			$user->status		= 1;
			$user->update		= date("Y-m-d H:m:s");
			$user->register_date= date("Y-m-d H:m:s");
			
			if ($user->save() == false) {
				foreach ($user->getMessages() as $item) {
					echo json_encode($item->getMessage());
				}
			} else {
				echo "1";
			}
		} else {
			echo "1";
		}
	}
	
	public function InsertGiftAction()
	{
		$contribution = new Contribution();
		$info = array();
		$info = $this->request->getJsonRawBody();
		
		$checkUser = Contribution::findFirst("id_facebook = ".$info->id_facebook);
		if ($checkUser == false) {
			$user->id_facebook 	= $info->id_facebook;
			$user->name			= $info->name;
			$user->last			= $info->last;
			$user->email		= $info->email;
			$user->birthday		= $info->birthday;
			$user->status		= 1;
			$user->update		= date("Y-m-d H:m:s");
			$user->register_date= date("Y-m-d H:m:s");
			
			if ($user->save() == false) {
				foreach ($user->getMessages() as $item) {
					echo json_encode($item->getMessage());
				}
			} else {
				echo "1";
			}
		} else {
			echo "1";
		}
	}	
	
}
