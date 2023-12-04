import { useEffect, useRef, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Sidebars.css';
import bgImg from '../../image/donggae.png';
import styles from "./Sidebar.module.css"



const Sidebar = () => {
    const navigate = useNavigate();
    
    const sidebarRef = useRef();
    
    return <div className='sidebar'>
        <div className="sidebar__logo">
        <img className="logoimg" src={bgImg} alt="Donggae Logo" />
        </div>
        <div ref={sidebarRef} className="sidebar__menu">
            <nav className={styles.navigation}>
                <ul>
                <li className={`sidebar__menu__item`}><Link to='/main'>메인</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/posting'>글 작성</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/userteam'>내 팀</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/rank'>순위</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/recruit'>팀원 모집 검색</Link></li>
                </ul>
            </nav>
           
        </div>
    </div>;
};

export default Sidebar;