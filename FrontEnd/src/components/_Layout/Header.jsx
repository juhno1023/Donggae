import styles from "./Header.module.css";
import { useState } from "react";
import { Link, useNavigate } from 'react-router-dom';

export default function Header() {

  const [searchValue, setSearch] = useState('');
  
  const saveSearch = event =>{
    setSearch(event.target.value);
    console.log(event.target.value);
  }
  const logout = () => {
    alert('로그아웃 되었습니다.')
    window.localStorage.clear();
}
  return (
    <div className={styles.header}>
      <div className={styles.contents}>
        <div className={styles.logo}>
          <div className={styles.title}><Link to='/main'>Donggae</Link></div>
          <div className={styles.subtitle}>
            <b>동</b>국대 <b>개</b>발자들
          </div>
        </div>
        {/* <div className={styles.search}>
          <input value={searchValue} onChange={saveSearch} type="text" placeholder="검색어 입력" />
          <img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
        </div> */}
        <div className={styles.userBox}>
          <span><Link to='/mypage'>마이페이지</Link></span>
          <span onClick={logout}><Link to='/'>로그아웃</Link></span>
        </div>
        <img className={styles.profileImg} src={localStorage.getItem('profile')}></img>
      </div>
    </div>
  );
}
